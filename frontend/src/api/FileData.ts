export default interface FileData {
  id?: number;
  name: string;
  size: number;
  owner: string;
  modifiedAt: Date;
  type?: string;
  data?: any;
}
