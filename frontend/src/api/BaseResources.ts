export interface ResourceEventTarget extends EventTarget {
  response: string;
}

export default class BaseResources {
  public static call(
    method: string,
    url: string,
    onLoadEnd: (e: ProgressEvent<any>) => void,
    params?: any,
    onLoadStart?: (e: ProgressEvent<any>) => void,
    onProgress?: (e: ProgressEvent<any>) => void
  ): void {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);

    if (onProgress) {
      xhr.onprogress = onProgress;
    }
    if (onLoadStart) {
      xhr.onloadstart = onLoadStart;
    }
    xhr.onloadend = onLoadEnd;

    xhr.send(params);
  }
}

export function isJSON(str: string) {
  try {
    JSON.parse(str);
  } catch (e) {
    return false;
  }
  return true;
}
